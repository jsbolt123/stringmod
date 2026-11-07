package com.example.stringmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class StringMod implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                // No .requires(...) here, so every player (even non-ops) can run it.
                dispatcher.register(CommandManager.literal("string").executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                    PlayerInventory inventory = player.getInventory();

                    int filled = 0;
                    // Main inventory + hotbar (36 slots). Armor and off-hand are left alone.
                    for (int i = 0; i < inventory.main.size(); i++) {
                        if (inventory.main.get(i).isEmpty()) {
                            inventory.setStack(i, new ItemStack(Items.STRING, Items.STRING.getMaxCount()));
                            filled++;
                        }
                    }

                    inventory.markDirty();
                    player.playerScreenHandler.sendContentUpdates();

                    final int count = filled;
                    if (count == 0) {
                        context.getSource().sendFeedback(() -> Text.literal("Your inventory has no empty slots."), false);
                    } else {
                        context.getSource().sendFeedback(
                                () -> Text.literal("Filled " + count + " empty slot" + (count == 1 ? "" : "s") + " with string."),
                                false);
                    }
                    return count;
                })));
    }
}
