package hu.yijun.tfcsacks.common.items;

import hu.yijun.tfcsacks.common.capabilities.LimitedContainer;
import hu.yijun.tfcsacks.common.container.TFCSacksContainerProviders;
import net.dries007.tfc.common.capabilities.DelegateItemHandler;
import net.dries007.tfc.common.capabilities.size.ItemSizeManager;
import net.dries007.tfc.common.capabilities.size.Size;
import net.dries007.tfc.common.recipes.inventory.EmptyInventory;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public class BurlapSackItem extends Item {

    public static final int SLOTS = 8;

    public BurlapSackItem(Properties properties) {
        super(properties);
    }

    @ParametersAreNonnullByDefault
    @Override
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {

        return super.overrideStackedOnOther(stack, slot, action, player);
    }


    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        if (!player.isShiftKeyDown() && !level.isClientSide() && player instanceof ServerPlayer serverPlayer)
        {
            TFCSacksContainerProviders.BURLAP_SACK.openScreen(serverPlayer, hand);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt)
    {
        return new BurlapSackCapability(stack);
    }

    @Override
    public int getMaxStackSize(ItemStack stack)
    {
        return 1;
    }

    public static class BurlapSackCapability implements ICapabilityProvider, DelegateItemHandler, LimitedContainer, EmptyInventory {
        private final ItemStack stack;
        private final ItemStackHandler inventory;

        private final LazyOptional<BurlapSackCapability> capability;

        BurlapSackCapability(ItemStack stack)
        {
            this.stack = stack;
            this.inventory = new ItemStackHandler(SLOTS);

            this.capability = LazyOptional.of(() -> this);
        }

        @Override
        public int getSlotStackLimit(int slot) {
            return 16;
        }

        @Override
        public void setAndUpdateSlots(int slot) {
            final ItemStack stack = inventory.getStackInSlot(slot);
            final CompoundTag tag = stack.getOrCreateTag();
            tag.put("inventory", inventory.serializeNBT());
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack)
        {
            return ItemSizeManager.get(stack).getSize(stack).isEqualOrSmallerThan(Size.SMALL);
        }

        @Override
        public @NotNull IItemHandlerModifiable getItemHandler()
        {
            return inventory;
        }

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction direction) {
            if (cap == ForgeCapabilities.ITEM_HANDLER) {
                final CompoundTag tag = stack.getOrCreateTag();
                inventory.deserializeNBT(tag.getCompound("inventory"));
                return capability.cast();
            }

            return LazyOptional.empty();
        }
    }
}
