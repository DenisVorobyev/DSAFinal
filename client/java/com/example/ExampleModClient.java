package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.text.Text;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ExampleModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("quiz").executes(context -> {
				Quiz.quizInstance = new Quiz(context.getSource().getPlayer());
				Quiz.quizInstance.nextQuestion();
				Quiz.quizInstance.printQuestion();
				return 1;
			}));
		});

	}
}