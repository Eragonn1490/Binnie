package binnie.genetics.machine.incubator;

import net.minecraft.tileentity.TileEntity;

import binnie.core.gui.minecraft.IMachineInformation;
import binnie.core.machines.Machine;
import binnie.core.machines.TileEntityMachine;
import binnie.core.machines.inventory.ComponentInventorySlots;
import binnie.core.machines.inventory.ComponentTankContainer;
import binnie.core.machines.inventory.InventorySlot;
import binnie.core.machines.power.ComponentPowerReceptor;
import binnie.genetics.core.GeneticsGUI;
import binnie.genetics.machine.ComponentGeneticGUI;
import binnie.genetics.machine.GeneticMachine;

public class PackageIncubator extends GeneticMachine.PackageGeneticBase implements IMachineInformation {
	public PackageIncubator() {
		super("incubator", 16767313);
	}

	@Override
	public void createMachine(final Machine machine) {
		new ComponentGeneticGUI(machine, GeneticsGUI.INCUBATOR);
		ComponentInventorySlots inventory = new ComponentInventorySlots(machine);
		for (InventorySlot slot : inventory.addSlotArray(Incubator.SLOT_QUEUE, getSlotRL("input"))) {
			slot.forbidExtraction();
		}
		InventorySlot slotIncubator = inventory.addSlot(Incubator.SLOT_INCUBATOR, getSlotRL("incubator"));
		slotIncubator.forbidInteraction();
		for (InventorySlot slot : inventory.addSlotArray(Incubator.SLOT_OUTPUT, getSlotRL("output"))) {
			slot.setReadOnly();
		}
		new ComponentPowerReceptor(machine, 2000);
		ComponentTankContainer tanks = new ComponentTankContainer(machine);
		tanks.addTank(Incubator.TANK_INPUT, "input", 2000).forbidExtraction();
		tanks.addTank(Incubator.TANK_OUTPUT, "output", 2000).setReadOnly();
		new IncubatorLogic(machine);
	}

	@Override
	public TileEntity createTileEntity() {
		return new TileEntityMachine(this);
	}

}
