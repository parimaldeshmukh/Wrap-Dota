package org.wrapdota.model;

class DotaMatchStructureDetails {
    Integer radiantTowerStatus;
    Integer direTowerStatus;
    Integer radiantBarrackStatus;
    Integer direBarrackStatus;

    public DotaMatchStructureDetails(Integer radiantTowerStatus, Integer direTowerStatus, Integer radiantBarrackStatus, Integer direBarrackStatus) {

        this.radiantTowerStatus = radiantTowerStatus;
        this.direTowerStatus = direTowerStatus;
        this.radiantBarrackStatus = radiantBarrackStatus;
        this.direBarrackStatus = direBarrackStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DotaMatchStructureDetails)) return false;

        DotaMatchStructureDetails that = (DotaMatchStructureDetails) o;

        if (!direBarrackStatus.equals(that.direBarrackStatus)) return false;
        if (!direTowerStatus.equals(that.direTowerStatus)) return false;
        if (!radiantBarrackStatus.equals(that.radiantBarrackStatus)) return false;
        if (!radiantTowerStatus.equals(that.radiantTowerStatus)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = radiantTowerStatus.hashCode();
        result = 31 * result + direTowerStatus.hashCode();
        result = 31 * result + radiantBarrackStatus.hashCode();
        result = 31 * result + direBarrackStatus.hashCode();
        return result;
    }
}