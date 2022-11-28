package me.justahuman.slimefuntoemi.recipetype;

import dev.emi.emi.EmiPort;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import me.justahuman.slimefuntoemi.SlimefunToEMI;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmelteryRecipe implements EmiRecipe {

    private final EmiRecipeCategory emiRecipeCategory;
    private final Identifier id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> outputs;
    private final int ticks;
    private final int energy;

    public SmelteryRecipe(EmiRecipeCategory emiRecipeCategory, Identifier id, List<EmiIngredient> inputs, List<EmiStack> outputs, int ticks, int energy) {
        this.emiRecipeCategory = emiRecipeCategory;
        this.id = id;
        this.inputs = inputs;
        this.outputs = outputs;
        this.ticks = ticks;
        this.energy = energy;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return this.emiRecipeCategory;
    }

    @Override
    public @Nullable Identifier getId() {

        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {

        return this.inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {

        return this.outputs;
    }

    @Override
    public int getDisplayWidth() {

        return 134;
    }

    @Override
    public int getDisplayHeight() {

        return 54;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        int offsetX = getDisplayWidth() / 2 - (SlimefunToEMI.chargeWidth + SlimefunToEMI.slotWidth * 3 + SlimefunToEMI.arrowWidth + SlimefunToEMI.bigSlotWidth + 20) /2;
        final int offsetYC = (getDisplayHeight() - SlimefunToEMI.chargeHeight) / 2;
        final int offsetYS = (getDisplayHeight() - SlimefunToEMI.slotHeight * 2) / 2;
        final int offsetYO = (getDisplayHeight() - SlimefunToEMI.bigSlotHeight) / 2;
        final int offsetYA = (getDisplayHeight() - SlimefunToEMI.arrowHeight) / 2;
        final int time = (int) (ticks / 20f * 1000);

        widgets.addTexture(SlimefunToEMI.EMPTY_CHARGE, offsetX, offsetYC);
        widgets.addAnimatedTexture(energy > 0 ? SlimefunToEMI.GAIN_CHARGE : SlimefunToEMI.LOOSE_CHARGE, offsetX, offsetYC, 1000, false, energy <= 0, energy <= 0).tooltip((mx, my) -> List.of(TooltipComponent.of(EmiPort.ordered(EmiPort.translatable("sftoemi.recipe.energy." + (energy > 0 ? "generate" : "use"), Math.abs(energy))))));
        offsetX = offsetX + SlimefunToEMI.chargeWidth + 4;
        widgets.addSlot(! inputs.isEmpty() ? inputs.get(0) : EmiStack.EMPTY, offsetX, offsetYS);
        widgets.addSlot(inputs.size() >= 4 ? inputs.get(3) : EmiStack.EMPTY, offsetX, offsetYS + SlimefunToEMI.slotHeight + 4);
        offsetX = offsetX + SlimefunToEMI.slotWidth + 4;
        widgets.addSlot(inputs.size() >= 2 ? inputs.get(1) : EmiStack.EMPTY, offsetX, offsetYS);
        widgets.addSlot(inputs.size() >= 5 ? inputs.get(4) : EmiStack.EMPTY, offsetX, offsetYS + SlimefunToEMI.slotHeight + 4);
        offsetX = offsetX + SlimefunToEMI.slotWidth + 4;
        widgets.addSlot(inputs.size() >= 3 ? inputs.get(2) : EmiStack.EMPTY, offsetX, offsetYS);
        widgets.addSlot(inputs.size() >= 6 ? inputs.get(5) : EmiStack.EMPTY, offsetX, offsetYS + SlimefunToEMI.slotHeight + 4);
        offsetX = offsetX + SlimefunToEMI.slotWidth + 4;
        widgets.addFillingArrow(offsetX, offsetYA, time).tooltip((mx, my) -> List.of(TooltipComponent.of(EmiPort.ordered(EmiPort.translatable("sftoemi.recipe.time", ticks / 20f, ticks)))));
        offsetX = offsetX + SlimefunToEMI.arrowWidth + 4;
        widgets.addSlot(! outputs.isEmpty() ? outputs.get(0) : EmiStack.EMPTY, offsetX, offsetYO).output(true);
    }

    @Override
    public boolean supportsRecipeTree() {
        return true;
    }
}
