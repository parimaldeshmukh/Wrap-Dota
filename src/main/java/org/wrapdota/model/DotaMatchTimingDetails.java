package org.wrapdota.model;


class DotaMatchTimingDetails {
    Long startTime;
    Long duration;
    Long firsBloodTime;

    public DotaMatchTimingDetails(Long startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DotaMatchTimingDetails)) return false;

        DotaMatchTimingDetails that = (DotaMatchTimingDetails) o;

        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (firsBloodTime != null ? !firsBloodTime.equals(that.firsBloodTime) : that.firsBloodTime != null)
            return false;
        if (!startTime.equals(that.startTime)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startTime.hashCode();
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (firsBloodTime != null ? firsBloodTime.hashCode() : 0);
        return result;
    }

    public void addDetails(Long duration, Long firstBloodTime) {
        this.duration = duration;
        this.firsBloodTime = firstBloodTime;
    }
}
