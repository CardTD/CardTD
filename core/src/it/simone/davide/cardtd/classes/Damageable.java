package it.simone.davide.cardtd.classes;

/**
 * This interface represents a damaging entity
 */
public interface Damageable {
    /**
     * @param damage
     * @return id dead or not
     */
    boolean damage(float damage);
}
