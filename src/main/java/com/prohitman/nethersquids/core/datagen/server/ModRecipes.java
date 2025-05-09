package com.prohitman.nethersquids.core.datagen.server;

import com.prohitman.nethersquids.core.init.ModBlocks;
import com.prohitman.nethersquids.core.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {
    public ModRecipes(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider) {
        super(pOutput, pLookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FIRE_BOOTS.get()).define('#', Blocks.MAGMA_BLOCK).define('&', Items.NETHERITE_INGOT).define('¿', ModItems.BEAK.get())
                .pattern("¿ ¿")
                .pattern("#&#")
                .unlockedBy("has_beak", has(ModItems.BEAK.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GRINDER.get()).define('#', Blocks.COBBLESTONE).define('&', ModItems.SAW.get()).define('$', ItemTags.PLANKS).define('@', Items.REDSTONE)
                .pattern("&&&")
                .pattern("$@$")
                .pattern("###")
                .unlockedBy("has_saw", has(ModItems.SAW.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SAW.get()).define('#', Items.IRON_INGOT).define('&', Items.IRON_NUGGET)
                .pattern("& &")
                .pattern(" # ")
                .pattern("& &")
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(consumer);
    }
}
