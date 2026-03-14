package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;

import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);
    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill teamASkill, Skill teamBSkill) {
        if (teamA == null || teamB == null || teamASkill == null || teamBSkill == null) {
            throw new IllegalArgumentException("Teams or skills cannot be null");
        }
        RaidResult result = new RaidResult();
        int rounds = 0;
        while (teamA.isAlive() && teamB.isAlive()) {

            rounds++;
            result.addLine("Round " + rounds);

            if (teamA.isAlive()) {
                result.addLine("Team A uses " + teamASkill.getSkillName());
                teamASkill.cast(teamB);
            }
            if (teamB.isAlive()) {
                result.addLine("Team B uses " + teamBSkill.getSkillName());
                teamBSkill.cast(teamA);
            }
        }
        result.setRounds(rounds);
        if (teamA.isAlive()) {
            result.setWinner("Team A");
        } else {
            result.setWinner("Team B");
        }
        return result;
    }
}
