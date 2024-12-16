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

@Mixin(ChatScreen.class)
public class ExampleClientMixin {
	@Inject(at = @At("HEAD"), method = "sendMessage", cancellable = true)
	private void sendMessage(String chatText, boolean addToHistory, CallbackInfo ci) {
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

