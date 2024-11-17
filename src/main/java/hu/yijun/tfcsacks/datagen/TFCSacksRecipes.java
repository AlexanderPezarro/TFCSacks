package hu.yijun.tfcsacks.datagen;

import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static hu.yijun.tfcsacks.common.items.TFCSacksItems.BURLAP_SACK;

public class TFCSacksRecipes extends RecipeProvider implements IConditionBuilder {

    public TFCSacksRecipes(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BURLAP_SACK.get())
                .pattern(" S ")
                .pattern("B|B")
                .pattern("BBB")
                .define('S', Tags.Items.STRING)
                .define('B', TFCItems.BURLAP_CLOTH.get())
                .define('|', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(TFCItems.BURLAP_CLOTH.get()), has(TFCItems.BURLAP_CLOTH.get()))
                .save(pWriter);
    }
}
