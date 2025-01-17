package net.clayborn.accurateblockplacement;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;

public class AccurateBlockPlacementMod implements ClientModInitializer
{
	public static Boolean disableNormalItemUse = false;
	public static boolean isAccurateBlockPlacementEnabled = true;

	public static MinecraftClient MC;
	
	@Override
	public void onInitializeClient()
	{
		MC = MinecraftClient.getInstance();

		KeyBinding keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("net.clayborn.accurateblockplacement.togglevanillaplacement", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Accurate Block Placement"));
		
		ClientTickEvents.END_CLIENT_TICK.register(e -> {
		    while(keybind.wasPressed()) {
				isAccurateBlockPlacementEnabled = !isAccurateBlockPlacementEnabled;
				
				MutableText message = null;
				
				if(isAccurateBlockPlacementEnabled) {
					message = net.minecraft.text.Text.translatable("net.clayborn.accurateblockplacement.modplacementmodemessage");
				}
				else {
					message = net.minecraft.text.Text.translatable("net.clayborn.accurateblockplacement.vanillaplacementmodemessage");
				}
				
				MC.inGameHud.getChatHud().addMessage(message);
			}
		});
	}
}