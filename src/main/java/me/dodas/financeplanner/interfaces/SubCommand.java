package me.dodas.financeplanner.interfaces;

import java.util.List;

public interface SubCommand {
        public String getName();
        public String getDescription();
        public String[] getAliases();
        public void executeCommand(List<String> args);       
}
