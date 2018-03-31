package leviathan143.polar.client;

import leviathan143.polar.common.Polar;
import leviathan143.polar.common.blocks.BlockRegistry;
import leviathan143.polar.common.items.ItemRegistry;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = Polar.MODID, value = Side.CLIENT)
public class ModelRegistry
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e)
	{
		ItemRegistry.registerModels();
		BlockRegistry.registerModels();
	}
}