package com.iafenvoy.iceandfire.screen.gui;

import com.iafenvoy.iceandfire.IceAndFire;
import com.iafenvoy.iceandfire.entity.EntityHippocampus;
import com.iafenvoy.iceandfire.screen.handler.HippocampusScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HippocampusScreen extends HandledScreen<HippocampusScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(IceAndFire.MOD_ID, "textures/gui/hippogryph.png");
    private float mousePosX;
    private float mousePosY;

    public HippocampusScreen(HippocampusScreenHandler dragonInv, PlayerInventory playerInv, Text name) {
        super(dragonInv, playerInv, name);
    }

    @Override
    protected void drawForeground(DrawContext pGuiGraphics, int mouseX, int mouseY) {
        int k = 0;
        int l = 0;
        assert MinecraftClient.getInstance().world != null;
        Entity entity = MinecraftClient.getInstance().world.getEntityById(this.handler.getHippocampusId());
        assert this.client != null;
        TextRenderer textRenderer = this.client.textRenderer;
        if (entity instanceof EntityHippocampus hippo)
            pGuiGraphics.drawText(textRenderer, hippo.getDisplayName().getString(), l + 8, 6, 4210752, false);
        pGuiGraphics.drawText(textRenderer, this.playerInventoryTitle, k + 8, l + this.backgroundHeight - 96 + 2, 4210752, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(context, mouseX, mouseY, partialTicks);
        this.mousePosX = mouseX;
        this.mousePosY = mouseY;
        super.render(context, mouseX, mouseY, partialTicks);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext pGuiGraphics, float partialTicks, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        pGuiGraphics.drawTexture(TEXTURE, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        assert MinecraftClient.getInstance().world != null;
        Entity entity = MinecraftClient.getInstance().world.getEntityById(this.handler.getHippocampusId());
        if (entity instanceof EntityHippocampus hippo) {
            if (hippo.isChested())
                pGuiGraphics.drawTexture(TEXTURE, i + 79, j + 17, 0, this.backgroundHeight, 5 * 18, 54);
            InventoryScreen.drawEntity(pGuiGraphics, i + 51, i + 100, j + 60, j + 100, 17, 0, i + 51 - this.mousePosX, j + 75 - 50 - this.mousePosY, hippo);
        }
    }
}