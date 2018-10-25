package net.shadowmage.ancientwarfare.structure.template.plugin.defaultplugins.blockrules;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowmage.ancientwarfare.core.util.WorldTools;
import net.shadowmage.ancientwarfare.structure.api.IStructureBuilder;

public class TemplateRuleVanillaSkull extends TemplateRuleBlockTile {
	public static final String PLUGIN_NAME = "vanillaSkull";
	private int skullRotation;

	public TemplateRuleVanillaSkull(World world, BlockPos pos, IBlockState state, int turns) {
		super(world, pos, state, turns);
		skullRotation = Rotation.values()[turns % 4].rotate(tag.getInteger("Rot"), 16);
	}

	public TemplateRuleVanillaSkull() {
		super();
	}

	@Override
	public void handlePlacement(World world, int turns, BlockPos pos, IStructureBuilder builder) {
		super.handlePlacement(world, turns, pos, builder);
		WorldTools.getTile(world, pos, TileEntitySkull.class).ifPresent(te -> te.setSkullRotation(Rotation.values()[turns % 4].rotate(skullRotation, 16)));
	}

	@Override
	protected ItemStack getStack() {
		return new ItemStack(Items.SKULL, 1, tag.getByte("SkullType"));
	}

	@Override
	public void writeRuleData(NBTTagCompound tag) {
		super.writeRuleData(tag);
		tag.setInteger("skullRotation", (short) skullRotation);
	}

	@Override
	public void parseRule(NBTTagCompound tag) {
		super.parseRule(tag);
		skullRotation = tag.getInteger("skullRotation");
	}

	@Override
	protected String getPluginName() {
		return PLUGIN_NAME;
	}
}
