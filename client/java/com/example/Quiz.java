package com.example;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.*;

/**
 * Quiz class represents a set of questions and the actions for getting them correct or incorrect
 */
public class Quiz {
    public static Quiz quizInstance;
    private PriorityQueue<Question> questions = new PriorityQueue<>();
    private Question currQuestion;
    private ClientPlayerEntity player;
    private static final List<Item> items = new ArrayList<>(Registries.ITEM.stream().toList());
    private static final String[] mobs = {
            "minecraft:blaze",
            "minecraft:creeper",
            "minecraft:elder_guardian",
            "minecraft:ender_dragon",
            "minecraft:endermite",
            "minecraft:evoker",
            "minecraft:ghast",
            "minecraft:giant",
            "minecraft:hoglin",
            "minecraft:husk",
            "minecraft:illusioner",
            "minecraft:magma_cube",
            "minecraft:phantom",
            "minecraft:piglin_brute",
            "minecraft:pillager",
            "minecraft:ravager",
            "minecraft:shulker",
            "minecraft:silverfish",
            "minecraft:skeleton",
            "minecraft:slime",
            "minecraft:stray",
            "minecraft:vex",
            "minecraft:vindicator",
            "minecraft:warden",
            "minecraft:witch",
            "minecraft:wither",
            "minecraft:wither_skeleton",
            "minecraft:zoglin",
            "minecraft:zombie",
            "minecraft:zombie_villager"
    };
    private static final Random random = new Random();

    /**
     * Consructs a new quiz
     * @param player the player to perform actions on
     */
    public Quiz(ClientPlayerEntity player) {
        this.player = player;
        questions.add(new Postfix(5));
        questions.add(new Postfix(6));
        questions.add(new Postfix(8));
        questions.add(new BaseAddition(5, 16));
        questions.add(new BaseAddition(4, 2));
        questions.add(new Bitwise(3));



    }

    /**
     * Print the question to the player's textbox
     */
    public void printQuestion() {
        player.sendMessage(Text.literal(getCurrQuestion().getText()), false);
    }

    /**
     * Advance the internal question state
     * @return the new question
     */
    public Question nextQuestion() {
        Question q = questions.remove();
        currQuestion = q;
        return q;
    }

    /**
     * Gets the current question
     * @return the current question
     */
    public Question getCurrQuestion() {
        return currQuestion;
    }

    /**
     * Ends the quiz due to a wrong answer. Spawns a random hostile mob near the player
     * @param guess the player's wrong guess
     */
    public void endQuiz(String guess) {
        player.sendMessage(Text.literal(guess + " is incorrect!"), false);
        String randomMobType = mobs[random.nextInt(mobs.length)];
        Vec3d playerPos = player.getPos();
        player.networkHandler.sendChatCommand("summon " + randomMobType + " " + playerPos.x + " " + playerPos.y + " " + playerPos.z);
        quizInstance = null;
    }

    /**
     * Give the player a random item in their inventory for a correct answer
     */
    public void correct() {
        player.sendMessage(Text.literal(getCurrQuestion().getAnswer() + " is the correct answer!"), false);
        Item randomItem = items.get(random.nextInt(items.size()));
        ItemStack stack = new ItemStack(randomItem, 1);
        player.getInventory().insertStack(stack);
    }
}