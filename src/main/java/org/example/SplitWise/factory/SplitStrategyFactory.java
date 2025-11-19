package org.example.SplitWise.factory;

import org.example.SplitWise.enums.SplitType;
import org.example.SplitWise.strategy.EqualSplitStrategy;
import org.example.SplitWise.strategy.PercentageSplitStrategy;
import org.example.SplitWise.strategy.SplitStrategy;

public class SplitStrategyFactory {
    public static SplitStrategy getStrategy(SplitType splitType) {
        return switch (splitType) {
            case EQUAL -> new EqualSplitStrategy();
            case PERCENTAGE -> new PercentageSplitStrategy();
        };
    }
}
