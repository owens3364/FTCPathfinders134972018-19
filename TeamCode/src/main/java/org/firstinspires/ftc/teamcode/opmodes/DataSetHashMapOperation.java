package org.firstinspires.ftc.teamcode.opmodes;

@FunctionalInterface
public interface DataSetHashMapOperation<Key, Value, Boolean> {
    Boolean apply(Key key, Value value);
}
