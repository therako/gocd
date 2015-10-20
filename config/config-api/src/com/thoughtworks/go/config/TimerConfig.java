/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.config;

import java.util.Map;

import com.thoughtworks.go.domain.ConfigErrors;
import org.quartz.CronExpression;


/**
 * @understands Configuration of a Pipeline cron timer
 */
@ConfigTag("timer")
public class TimerConfig implements Validatable {
    @ConfigAttribute(value = "onlyOnChanges", optional = true, allowNull = true) private Boolean onlyOnChanges = false;
    @ConfigValue private String timerSpec;

    public static final String TIMER_SPEC = "timerSpec";
    public static final String TIMER_ONLY_ON_CHANGES = "onlyOnChanges";
    private final ConfigErrors errors = new ConfigErrors();

    public TimerConfig() {
    }

    public TimerConfig(String timerSpec, boolean onlyOnChanges) {
        this.timerSpec = timerSpec;
        this.onlyOnChanges = onlyOnChanges;
    }

    public String getTimerSpec() {
        return timerSpec;
    }

    public boolean shouldTriggerOnlyOnChanges() {
        return onlyOnChanges;
    }

    //Only for Rails
    public boolean getOnlyOnChanges(){
        return onlyOnChanges;
    }

    public static TimerConfig createTimer(Object attributes) {
        Map timerConfigMap = (Map) attributes;
        String timerSpec = (String) timerConfigMap.get(TIMER_SPEC);
        if (timerSpec.isEmpty()) {
            return null;
        }
        String onlyOnChanges = (String) timerConfigMap.get(TIMER_ONLY_ON_CHANGES);
        return new TimerConfig(timerSpec, "1".equals(onlyOnChanges));
    }

    public void validate(ValidationContext validationContext) {
        boolean isValid = CronExpression.isValidExpression(timerSpec);
        if (!isValid) {
            errors.add(TIMER_SPEC, "Invalid cron syntax");
        }
    }

    public ConfigErrors errors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(fieldName, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimerConfig that = (TimerConfig) o;

        if (timerSpec != null ? !timerSpec.equals(that.timerSpec) : that.timerSpec != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return timerSpec != null ? timerSpec.hashCode() : 0;
    }
}
