public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(printCB)) {
            demoInstVars.getPrintCB().setSelected(printCB.isSelected());
        } else if (e.getSource().equals(delayTextField)) {
            delay = Integer.parseInt(delayTextField.getText().trim());
        } else if (e.getSource().equals(runsTextField)) {
            numRuns = Integer.parseInt(runsTextField.getText().trim());
        } else if ("Run".equals(e.getActionCommand())) {
            doRunAction();
        } else if ("Stop".equals(e.getActionCommand())) {
            stop();
        }
    