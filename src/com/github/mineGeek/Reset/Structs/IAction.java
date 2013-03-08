package com.github.mineGeek.Reset.Structs;

import java.util.List;

public interface IAction {
	public void close();
	public void run();
	public void ini();
	public void start();
	public void stop();
	public List<IAction> preActions();
	public List<IAction> postActions();
	
	
}
