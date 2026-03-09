package tinkers.dummy.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tinkers.dummy.TinkersReborn;
import tinkers.dummy.block.menu.TinkersStationMenu;

public class TinkersStationScreen extends AbstractContainerScreen<TinkersStationMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(TinkersReborn.MODID, "textures/gui/tinkers_station.png");

    public TinkersStationScreen(TinkersStationMenu menu, Inventory playerInventory, Component title) {
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
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // 4210752 is the standard 'Dark Gray' for Minecraft GUIs
        // If it looks too dark, ensure your background PNG isn't too dark gray
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);

        // playerInventoryTitle is "Inventory" from your lang file
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }


}