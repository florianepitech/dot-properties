package net.dot.properties.fields.implementations;

import net.dot.properties.fields.PropertyField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IntegerPropertyField extends PropertyField {

    public IntegerPropertyField() {
        super(Integer.class);
    }

    @Override
    public @NotNull Object parseString(@NotNull final String value) {
        return (Integer.parseInt(value));
    }
}
