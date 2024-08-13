package com.prohitman.nethersquids.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.prohitman.nethersquids.NetherSquids;
import com.prohitman.nethersquids.client.menu.GrinderMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GrinderScreen extends AbstractContainerScreen<GrinderMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NetherSquids.MODID, "textures/gui/grinder_gui.png");

    public GrinderScreen(GrinderMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        this.imageHeight = 174;
        this.imageWidth = 175;
        super.init();

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, 175, 174);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {//39 hieght, 32 width
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 62, y + 22, 223, 0, menu.getScaledProgress(), 39);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics, mouseX, mouseY, delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
