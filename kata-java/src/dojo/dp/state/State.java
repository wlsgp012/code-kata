package dojo.dp.state;

public interface State {
    void doClock(Context context, int hour);

    void doUse(Context context);

    void doAlarm(Context context);

    void doPhone(Context context);
}
