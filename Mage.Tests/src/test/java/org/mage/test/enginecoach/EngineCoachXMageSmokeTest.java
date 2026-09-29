package org.mage.test.enginecoach;

import mage.constants.PhaseStep;
import mage.constants.Zone;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 * Engine Coach — XMage Integration Feasibility X1
 * Pinned upstream basis: magefree/mage@3d3f4320ed0bb07cb2331f29e3d7cdadf5746ac0
 * CI trigger refresh after enabling GitHub Actions.
 */
public class EngineCoachXMageSmokeTest extends CardTestPlayerBase {

    @Test
    public void keen_SaiArtifactCastCreatesExactlyOneThopter() {
        setStrictChooseMode(true);
        addCard(Zone.BATTLEFIELD, playerA, "Sai, Master Thopterist");
        addCard(Zone.BATTLEFIELD, playerA, "Island");
        addCard(Zone.HAND, playerA, "Aether Spellbomb");

        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Aether Spellbomb");
        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertPermanentCount(playerA, "Aether Spellbomb", 1);
        assertTokenCount(playerA, "Thopter Token", 1);
    }

    @Test
    public void reign_LathlissTokenDoesNotSelfRecurse() {
        setStrictChooseMode(true);
        addCard(Zone.BATTLEFIELD, playerA, "Lathliss, Dragon Queen");
        addCard(Zone.BATTLEFIELD, playerA, "Mountain", 5);
        addCard(Zone.HAND, playerA, "Rapacious Dragon");

        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Rapacious Dragon");
        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertTokenCount(playerA, "Dragon Token", 1);
    }

    @Test
    public void tramp_GhaltaCostReductionUsesBoardPower() {
        setStrictChooseMode(true);
        addCard(Zone.BATTLEFIELD, playerA, "Forest", 2);
        addCard(Zone.BATTLEFIELD, playerA, "Grizzly Bears", 5);
        addCard(Zone.HAND, playerA, "Ghalta, Primal Hunger");

        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, "Ghalta, Primal Hunger");
        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertPermanentCount(playerA, "Ghalta, Primal Hunger", 1);
    }
}
