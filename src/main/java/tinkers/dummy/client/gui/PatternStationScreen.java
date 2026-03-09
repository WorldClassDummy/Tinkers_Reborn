package tinkers.dummy.client.gui; // Or your screen package

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.block.menu.CraftingStationMenu;

public class PatternStationScreen extends AbstractContainerScreen<CraftingStationMenu> {
    // Re-use your existing texture for now
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(TinkersReborn.MODID, "textures/gui/tinkers_station.png");

    public PatternStationScreen(CraftingStationMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Parameters: Texture, x, y, uOffset, vOffset, uWidth, vWidth, textureWidth, textureHeight
        // If your PNG file is 176x166, the last two numbers MUST be 176, 166.
        // If your PNG file is 256x256, the last two numbers MUST be 256, 256.
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 176, 166);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        // In new versions, this now takes mouse coordinates and delta
        this.renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}