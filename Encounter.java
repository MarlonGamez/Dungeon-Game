public abstract interface Encounter
{
    public abstract void Attack( Encounter target );
    public abstract int getHP();
    public abstract void setHP( int newHP );
    public abstract String getName();
    public abstract int getGold();
    public abstract void setGold( Encounter x );
}