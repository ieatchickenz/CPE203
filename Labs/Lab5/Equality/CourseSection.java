import java.time.LocalTime;

class CourseSection
{
    private final String prefix;
    private final String number;
    private final int enrollment;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public CourseSection(final String prefix, final String number,
                         final int enrollment, final LocalTime startTime, final LocalTime endTime)
    {
        this.prefix = prefix;
        this.number = number;
        this.enrollment = enrollment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean equals(Object other){
        if (other == null)
            return false;
        if (!this.getClass().equals(other.getClass()))
            return false;
        CourseSection o = (CourseSection)other;
        if ((o.prefix == null && this.prefix != null) || (o.prefix != null && this.prefix == null))
            return false;
        if ((o.number == null && this.number != null) || (o.number != null && this.number == null))
            return false;
        if ((o.endTime == null && this.endTime != null) || (o.endTime != null && this.endTime == null))
            return false;
        if ((o.startTime == null && this.startTime != null) || (o.startTime != null && this.startTime == null))
            return false;

        return (o.prefix.equals(this.prefix) &&
                o.number.equals(this.number) &&
                o.endTime.equals(this.endTime) &&
                o.enrollment == this.enrollment &&
                o.startTime.equals(this.startTime));
    }

    public int hashCode(){
        int code = 0;
        if (prefix != null)
            code += prefix.hashCode();
        if (number != null)
            code += number.hashCode();
        if (enrollment != 0)
            code += enrollment;
        if(startTime != null)
            code += startTime.hashCode();
        if (endTime != null)
            code += endTime.hashCode();
        return code;
    }
    // additional likely methods not defined since they are not needed for testing
}