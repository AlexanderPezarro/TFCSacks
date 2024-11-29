package hu.yijun.tfcsacks.client.screen;

import hu.yijun.tfcsacks.common.container.ToolSackContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import static hu.yijun.tfcsacks.TFCSacks.MOD_ID;
import static net.dries007.tfc.util.Helpers.resourceLocation;

public class ToolSackInventoryScreen extends AbstractContainerScreen<ToolSackContainer> {

    // TODO
    public final ResourceLocation INVENTORY_4x2 = resourceLocation(MOD_ID, "textures/gui/eight_inventory.png");

    public ToolSackInventoryScreen(ToolSackContainer container, Inventory inventory, Component ptitle) {
        super(container, inventory, ptitle);
    }

    @Override
    public void render(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground(poseStack);
    }

    protected void drawDefaultBackground(GuiGraphics graphics) {
        graphics.blit(INVENTORY_4x2, leftPos, topPos, 0, 0, 0, imageWidth, imageHeight, 256, 256);
    }
}