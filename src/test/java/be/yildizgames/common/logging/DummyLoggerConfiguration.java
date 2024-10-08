/*
 This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 Copyright (c) 2018-2024 Grégory Van den Borre
 More infos available: https://engine.yildiz-games.be
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright
 notice and this permission notice shall be included in all copies or substantial portions of the  Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package be.yildizgames.common.logging;

import java.util.List;

/**
 * @author Grégory Van den Borre
 */
public class DummyLoggerConfiguration implements LoggerConfiguration {

    @Override
    public String getLoggerPattern() {
        return "%n";
    }

    @Override
    public LoggerLevel getLoggerLevel() {
        return LoggerLevel.DEBUG;
    }

    @Override
    public List<SupportedOutput> getLoggerOutputs() {
        return List.of(SupportedOutput.FILE);
    }

    @Override
    public String getLoggerTcpHost() {
        return "localhost";
    }

    @Override
    public int getLoggerTcpPort() {
        return 25;
    }

    @Override
    public String getLoggerOutputFile() {
        return "/output";
    }

    @Override
    public String getLoggerConfigurationFile() {
        return "config";
    }

    @Override
    public List<String> getLoggerToDisable() {
        return List.of();
    }
}
