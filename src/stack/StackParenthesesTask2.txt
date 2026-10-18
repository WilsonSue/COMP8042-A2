 public void run() {

        System.out.println("\nJ2D Demo RunWindow : " + numRuns + " Runs, "
                + delay + " second delay between tabs\n" + "java version: " + System.
                getProperty("java.version") + "\n" + System.getProperty(
                "os.name") + " " + System.getProperty("os.version") + "\n");
        Runtime r = Runtime.getRuntime();

        for (int runNum = 0; runNum < numRuns && thread != null; runNum++) {

            Date d = new Date();
            System.out.print("#" + runNum + " " + d.toString() + ", ");
            r.gc();
            float freeMemory = r.freeMemory();
            float totalMemory = r.totalMemory();
            System.out.println(((totalMemory - freeMemory) / 1024) + "K used");

            for (int i = 0; i < demoInstVars.getTabbedPane().getTabCount() && thread
                    != null; i++) {

                final int mainTabIndex = i;
                Runnable initDemoRunnable = new Runnable() {

                    @Override
                    public void run() {
                        pb.setValue(0);
                        pb.setMaximum(delay);
                        if (mainTabIndex != 0) {
                            dg = demoInstVars.getGroup()[mainTabIndex - 1];
                            dg.invalidate();
                        }
                        demoInstVars.getTabbedPane().setSelectedIndex(mainTabIndex);
                    }
                };
                invokeAndWait(initDemoRunnable);

                if (i != 0 && (zoomCB.isSelected() || buffersFlag)) {
                    dp = (DemoPanel) dg.getPanel().getComponent(0);
                    if (dg.tabbedPane == null && dp.surface != null) {
                        Runnable mouseClickedRunnable = new Runnable() {

                            @Override
                            public void run() {
                                dg.mouseClicked(dp.surface);
                            }
                        };
                        invokeAndWait(mouseClickedRunnable);
                    }
                    for (int j = 1; j < dg.tabbedPane.getTabCount() && thread
                            != null; j++) {

                        final int subTabIndex = j;

                        Runnable initPanelRunnable = new Runnable) {

                            @Override
                            public void run() {
                                pb.setValue(0);
                                pb.setMaximum(delay);
                                dg.tabbedPane.setSelectedIndex(subTabIndex);
                            }
                        };
                        invokeAndWait(initPanelRunnable);

                        final JPanel p = dg.getPanel();
                        if (buffersFlag && p.getComponentCount() == 1) {
                            dp = (DemoPanel) p.getComponent(0);
                            if (dp.surface.animating != null) {
                                dp.surface.animating.stop();
                            }
                            for (int k = bufBeg; k <= bufEnd && thread != null;
                                    k++) {

                                final int cloneIndex = k;
                                Runnable cloneRunnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        dp.tools.cloneB.doClick();
                                        int n = p.getComponentCount();
                                        DemoPanel clone = (DemoPanel) p.
                                                getComponent(n - 1);
                                        if (clone.surface.animating != null) {
                                            clone.surface.animating.stop();
                                        }
                                        clone.tools.issueRepaint = true;
                                        clone.tools.screenCombo.setSelectedIndex(
                                                cloneIndex);
                                        clone.tools.issueRepaint = false;
                                    }
                                };
                                invokeAndWait(cloneRunnable);
                            }
                        }
                        if (printCB.isSelected()) {
                            printDemo(dg);
                        }
                        sleepPerTab();
                    }
                } else if (i != 0 && printCB.isSelected()) {
                    printDemo(dg);
                    sleepPerTab();
                } else {
                    sleepPerTab();
                }
            }
            if (runNum + 1 == numRuns) {
                System.out.println("Finished.");
                if (exit && thread != null) {
                    System.out.println("System.exit(0).");
                    System.exit(0);
                }
            }
        }
        Runnable resetRunnable = new Runnable() {

            @Override
            public void run( {
                runB.setText("Run");
                runB.setBackground(GREEN);
                pb.setValue(0);
            }
        };
        invokeAndWait(resetRunnable);

        thread = null;
        dg = null;
        dp = null;
    }