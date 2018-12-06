package org.firstinspires.ftc.teamcode.opmodes;

@FunctionalInterface
public interface DataSetHashMapOperation<Key, Value, Return> {
    Return apply(Key key, Value value);
}
