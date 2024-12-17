package com.example.mixin.client;

import com.example.Quiz;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.security.auth.callback.Callback;

/**
 * Code that is injected in the Minecraft Client
 */
@Mixin(ChatScreen.class)
public class ExampleClientMixin {

	/**
	 * Intercepts any message sent by the user and checks it against the current quiz question
	 * if there is a current quiz instance active
	 * @param chatText the content of the message
	 * @param addToHistory whether the message was added to chat history
	 * @param ci the callback object used for cancelling the method
	 */
	@Inject(at = @At("HEAD"), method = "sendMessage", cancellable = true)
	private void sendMessage(String chatText, boolean addToHistory, CallbackInfo ci) {
		if (chatText.contains("Summoned")) {
			ci.cancel();
		}

		if (Quiz.quizInstance != null) {
			if (Quiz.quizInstance.getCurrQuestion().correct(chatText)) {
				Quiz.quizInstance.correct();
				Quiz.quizInstance.nextQuestion();
				Quiz.quizInstance.printQuestion();
			} else {
				Quiz.quizInstance.endQuiz(chatText);
			}
			ci.cancel();

		}
	}
}

