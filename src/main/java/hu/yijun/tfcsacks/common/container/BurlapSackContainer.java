package hu.yijun.tfcsacks.common.container;

import hu.yijun.tfcsacks.common.capabilities.LimitedContainer;
import net.dries007.tfc.common.container.CallbackSlot;
import net.dries007.tfc.common.container.ItemStackContainer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.items.IItemHandler;

public class BurlapSackContainer extends ItemStackContainer {

    public static BurlapSackContainer create(ItemStack stack, InteractionHand hand, int slot, Inventory playerInv, int windowId)
    {
        return new BurlapSackContainer(stack, hand, slot, playerInv, windowId).init(playerInv);
    }

    private final LimitedContainer limitedContainer;


    private BurlapSackContainer(ItemStack stack, InteractionHand hand, int slot, Inventory playerInv, int windowId) {
        super(TFCSacksContainerTypes.BURLAP_SACK_CONTAINER.get(), windowId, playerInv, stack, hand, slot);
        Capability<IItemHandler> itemHandlerCapability = CapabilityManager.get(new CapabilityToken<>() {
        });
        var itemHandler = stack.getCapability(itemHandlerCapability).orElse(null);
        limitedContainer = itemHandler instanceof LimitedContainer lc ? lc : null;
    }

    @Override
    protected void addContainerSlots()
    {
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 53, 23));
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 71, 23));
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 89, 23));
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 107, 23));

        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 53, 41));
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 71, 41));
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 89, 41));
        addSlot(new CallbackSlot(limitedContainer, limitedContainer, 0, 107, 41));
    }
}
