package mocks;

import impl.ConcreteSpell;

import java.util.Map;

import static abstracts.Consts.DAMAGE_ENEMY_MINIONS_ABILITY;
import static abstracts.Consts.SUMMON_ABILITY;
import static abstracts.Consts.TRANSFORM_INTO_ABILITY;

public class SpellMock extends ConcreteSpell {

    public SpellMock(ConcreteSpell concreteSpell) {
        super(concreteSpell.getType(),
                concreteSpell.getRequiredMana(),
                concreteSpell.getBonus(),
                concreteSpell.getAbilityKeyWord(),
                concreteSpell.getName(),
                concreteSpell.getImgurl(),
                concreteSpell.getText());
        setPlayer(concreteSpell.getPlayer());
    }

    @Override
    public void generateSpellEffect(Map<String,String> abilityKeyWord) {
        for (Map.Entry<String, String> entry : abilityKeyWord.entrySet()) {

            switch (entry.getKey()) {

                case SUMMON_ABILITY:
                    SummonMock abilitySummon = new SummonMock(entry.getValue(), this);
                    this.getMyEffects().add(abilitySummon);
                    break;
                case TRANSFORM_INTO_ABILITY:
                    TransformIntoMock abilityTransform = new TransformIntoMock(entry.getValue());
                    this.getMyEffects().add(abilityTransform);
                    break;
                case DAMAGE_ENEMY_MINIONS_ABILITY:

                default:
                    break;
            }
        }
    }
}
