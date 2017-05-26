package com.example.asus.lab.bmp;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class S_Log {
	private S_File file;
	private double begtime;
	private ArrayList<String> list;
	private static S_Log debug=null;
	
	public S_Log(String s){
		file=new S_File(s+".txt",true);
		file.Set(B2B((byte)0));
		file.Write();
		file.SetAdd(true);
		begtime=System.currentTimeMillis();
		System.setErr(new PrintStream(new OutputStream() {
			String str="";

			@Override
			public void write(int b) throws IOException {
				if(b=='\n'){
					AddLine(str);
					str="";
				}else{
					str+=(char)b;
				}
			}
		}));
	}
	
	public S_Log(){
		file=null;
		list=new ArrayList<String>();
		begtime=System.currentTimeMillis();

	}
	
	public S_File GetFile(){
		return file;
	}
	
	public static byte[] B2B(byte...p){
		return p;
	}
	
	public static byte[] S2B(String s){
		byte r[]=new byte[s.length()];
		for(int i=0;i<s.length();i++){
			r[i]=(byte) s.charAt(i);
		}
		return r;
	}
	
	public synchronized void AddLine(String s){
		String r=(System.currentTimeMillis()-begtime)+" | "+s+'\n';
		if(file!=null){
			file.Set(r.getBytes());
			file.Write();
		}else{
			list.add(r);
		}
	}
	
	public synchronized void AddLine(Exception e){
		StackTraceElement st[]=e.getStackTrace();
		String s=e.toString();
		for(StackTraceElement ste:st){
			s+="\n"+ste.toString();
		}
		AddLine(s);
	}
	
	public void enable(String name){
		if(file!=null){
			return;
		}
		file=new S_File(name+".txt",true);
		file.Set(B2B((byte)0));
		file.Write();
		file.SetAdd(true);
		for(String s:list){
			AddLine(s);
		}
	}
	
	public static S_Log getDebugLog(){
		if(debug==null){
			debug=new S_Log();
		}
		return debug;
	}
	
	public static S_Log initDebugLog(String name, boolean enabled){
		if(!enabled){
			return getDebugLog();
		}
		if(debug==null){
			debug=new S_Log(name);
		}
		return debug;
	}

}
