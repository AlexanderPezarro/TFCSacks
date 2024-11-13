package hu.yijun.tfcsacks.common.container;

import net.dries007.tfc.common.container.ItemStackContainer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class BurlapSackContainer extends ItemStackContainer {

    public static BurlapSackContainer create(ItemStack stack, InteractionHand hand, int slot, Inventory playerInv, int windowId)
    {
        return new BurlapSackContainer(stack, hand, slot, playerInv, windowId).init(playerInv);
    }

    private BurlapSackContainer(ItemStack stack, InteractionHand hand, int slot, Inventory playerInv, int windowId) {
        super(TFCSacksContainerTypes.BURLAP_SACK_CONTAINER.get(), windowId, playerInv, stack, hand, slot);

    }

    @Override
    protected void addContainerSlots()
    {
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 53, 23));
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 71, 23));
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 89, 23));
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 107, 23));

        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 53, 41));
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 71, 41));
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 89, 41));
        addSlot(new SlotItemHandler(new ItemStackHandler(1), 0, 107, 41));
    }
}
