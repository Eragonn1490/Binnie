package binnie.genetics.craftgui;

import binnie.core.AbstractMod;
import binnie.core.craftgui.geometry.Position;
import binnie.core.craftgui.minecraft.GUIIcon;
import binnie.core.craftgui.minecraft.Window;
import binnie.core.craftgui.minecraft.control.ControlEnergyBar;
import binnie.core.craftgui.minecraft.control.ControlErrorState;
import binnie.core.craftgui.minecraft.control.ControlIconDisplay;
import binnie.core.craftgui.minecraft.control.ControlLiquidTank;
import binnie.core.craftgui.minecraft.control.ControlMachineProgress;
import binnie.core.craftgui.minecraft.control.ControlPlayerInventory;
import binnie.core.craftgui.minecraft.control.ControlSlot;
import binnie.core.craftgui.minecraft.control.ControlSlotArray;
import binnie.core.craftgui.minecraft.control.ControlSlotCharge;
import binnie.core.craftgui.resource.Texture;
import binnie.core.craftgui.resource.minecraft.StandardTexture;
import binnie.extrabees.core.ExtraBeeTexture;
import binnie.genetics.Genetics;
import binnie.genetics.machine.Genepool;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class WindowGenepool extends WindowMachine {
	static Texture ProgressBase = new StandardTexture(64, 0, 130, 21, ExtraBeeTexture.GUIProgress.getTexture());
	static Texture Progress = new StandardTexture(64, 21, 130, 21, ExtraBeeTexture.GUIProgress.getTexture());

	public WindowGenepool(EntityPlayer player, IInventory inventory, Side side) {
		super(280, 198, player, inventory, side);
	}

	public static Window create(EntityPlayer player, IInventory inventory, Side side) {
		return new WindowGenepool(player, inventory, side);
	}

	@Override
	public void initialiseClient() {
		setTitle("Genepool");
		int x = 16;
		int y = 32;
		new ControlLiquidTank(this, x, y).setTankID(1);
		x += 26;
		new ControlSlotArray(this, x, y + 3, 2, 3).create(Genepool.slotReserve);
		x += 38;
		new ControlIconDisplay(this, x, y + 3 + 18 + 1, GUIIcon.ArrowRight.getIcon());
		x += 18;
		new ControlSlot(this, x, y + 3 + 18).assign(0);
		x += 18;
		new ControlMachineProgress(this, x, y + 19, WindowGenepool.ProgressBase, WindowGenepool.Progress, Position.Left);
		x += 130;
		new ControlLiquidTank(this, x, y).setTankID(0);
		new ControlEnergyBar(this, 21, 115, 16, 60, Position.Bottom);
		new ControlSlot(this, 121.0f, 82.0f).assign(7);
		new ControlSlotCharge(this, 143, 82, 7).setColor(15722671);
		new ControlErrorState(this, 181.0f, 83.0f);
		new ControlPlayerInventory(this);
	}

	@Override
	public String getTitle() {
		return "Genepool";
	}

	@Override
	protected AbstractMod getMod() {
		return Genetics.instance;
	}

	@Override
	protected String getName() {
		return "Genepool";
	}
}