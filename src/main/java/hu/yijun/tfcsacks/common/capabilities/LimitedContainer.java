package hu.yijun.tfcsacks.common.capabilities;

import net.dries007.tfc.common.container.ISlotCallback;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public interface LimitedContainer extends IItemHandler, ISlotCallback {

    @Override
    boolean isItemValid(int i, @NotNull ItemStack itemStack);
}
