package com.example.asus.lab.bmp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class S_File {
	byte data[];
	String filename;
	private File sfile;
	private boolean extern,is,add;
	
	public S_File(S_File d,String n,boolean isd){
		filename=n;
		File dr;
		if(d==null){
			dr=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		}else{
			dr=d.GetFile();
		}
		sfile=new File(dr,filename);
		if(!isd){
			extern=true;
			is=true;
			
		}
	}
	
	public File GetFile(){
		return sfile;
	}
	
	public S_File(byte a){
		Set(a);
		extern=false;
	}
	
	public S_File(){
		extern=false;
	}
	
	public S_File(String n){
		filename=n;
		extern=false;
	}
	
	public S_File(File f){
		sfile=f;
		filename=f.getName();
		extern=true;
	}
	
	public S_File(String n,String p){
		sfile=new File(p,n);
		filename=n;
		extern=true;
		add=false;
	}
	
	public S_File(String n,boolean e){
		filename=n;
		extern=e;
		add=false;
		ExtFile(n);
	}
	/*
	public boolean IsFile(){
		boolean a=false;
		String fl[]=Engine.get().getContext().fileList();
		if(fl!=null&&fl.length>0){
			for(int i=0;i<fl.length&&!a;i++){
				if(fl[i].hashCode()==filename.hashCode()){
					a=true;
				}
			}
		}
		return a;
	}
	
	/*
	public static boolean IsFile(String s){
		boolean a=false;
		String fl[]=Engine.get().getContext().fileList();
		if(fl!=null&&fl.length>0){
			for(int i=0;i<fl.length&&!a;i++){
				if(fl[i].hashCode()==s.hashCode()){
					a=true;
				}
			}
		}
		return a;
	}
	*/
	public int GetInt(){
		if(data!=null&&data.length>0){
		int r=0,d1=0,d2;
		for(int i=0;i<4;i++){
			d1=data[i];
			if(d1<0){
				d1*=-1;
				d1=256-d1;
			}
			d2=(int) (d1*Math.pow(256,3-i));
			r+=d2;
		}
		return r;
		}
		return 0;
	}
	
	public void SetInt(int s){
		int r=s;
		int d1;
		data=new byte[4];
		for(int i=0;i<4;i++){
			data[i]=(byte) (r/Math.pow(256,3-i));
			d1=data[i];
			if(d1<0){
				d1*=-1;
				d1=256-d1;
			}
			r-=d1*Math.pow(256,3-i);
		}
	}
	
	public OutputStream GetOutputStream(){
		OutputStream stream=null;
		/*
		if(!extern){
			try {
				stream=Engine.get().getContext().openFileOutput(filename, Context.MODE_PRIVATE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{*/
			try {
				stream = new FileOutputStream(sfile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		//}
		return stream;
	}
	
	public InputStream GetInputStream(){
		InputStream stream=null;
		/*if(!extern){
			try {
				stream=Engine.get().getContext().openFileInput(filename);;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}else{*/
			try {
				stream = new FileInputStream(sfile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		//}
		return stream;
	}
	
	public long Size(){
		int r=0;
		if(extern&&sfile!=null){
			return sfile.length();
		}
		try {
			InputStream stream=GetInputStream();
			r=stream.available();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public void Read(OutputStream stream){
		InputStream in=GetInputStream();
		long l=0;
		if(in==null){
			return;
		}
		int len=0;
		byte buf[]=new byte[1024*64];
		try {
			if(extern&&sfile!=null){
				l=sfile.length();
			}else{
				l=in.available();
			}
			//ProgressBar.bar.Start(l);
			
			while ((len = in.read(buf)) != -1) {
			    stream.write(buf, 0, len);
			    //ProgressBar.bar.Update(len);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Write(InputStream stream){
		OutputStream out=GetOutputStream();
		if(out==null){
			return;
		}
		int len=0;
		byte buf[]=new byte[1024*64];
		try {
			while ((len = stream.read(buf)) != -1) {
			    out.write(buf, 0, len);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	public void Read(String name){
		//data=new byte[20];
		try {
			FileInputStream fos=Engine.get().getContext().openFileInput(name);
			data=new byte[fos.available()];
			fos.read(data);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if(data!=null){
			//return data[0];
		//}else{
		//	return 0;
		//}
	}
	*/
	
	
	
	public byte[] Read(){
		data=null;
		//if(is)
		/*if(!extern){
			try {
				FileInputStream fos=Engine.get().getContext().openFileInput(filename);;
				data=new byte[fos.available()];
				fos.read(data);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{*/
			InputStream in = null;
			try {
				in = new BufferedInputStream(new FileInputStream(sfile));
				data=new byte[in.available()];
				in.read(data);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
			     if (in != null) {
			    	 try {
			    		 in.close();
			    	 } catch (IOException e) {
			    		 e.printStackTrace();
			    	 }
			     }
			}
		//}
		return data;
	}
	
	public void ExtFile(String fnm){
		sfile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fnm);
		if(sfile.isFile()&&sfile.canWrite()&&sfile.canRead()){
			extern=true;
			filename=fnm;
			is=true;
		}else{
			extern=true;
			filename=fnm;
			is=true;
			data=new byte[1];
			data[0]=0;
			Write();
			if(sfile.isFile()&&sfile.canWrite()&&sfile.canRead()){
			}else{
				sfile=null;
				extern=false;
				filename=fnm;
				is=false;
			}
		}
	}
	
	public static String Act(){
		String abc="a";
		byte a[]=new byte[2];
		a[0]=20;a[1]=50;
		Write(a,"file.txt");
		//a=Read("file.txt");
		abc=Byte.toString(a[0]);
		return abc;
	}
	
	public void Set(byte a[]){
		data=new byte[a.length];
		for(int i=0;i<a.length;i++){
			data[i]=a[i];
		}
	}
	
	public void Set(byte a){
		data=new byte[1];
		data[0]=a;
	}
	/*
	public void Write(String name){
		try {
			FileOutputStream fos = Engine.get().getContext().openFileOutput(name, Context.MODE_PRIVATE);
			fos.write(data);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	public void SetAdd(boolean a){
		add=a;
	}
	
	public void Write(){
		/*if(!extern){
			try {
				FileOutputStream fos = Engine.get().getContext().openFileOutput(filename,Context.MODE_PRIVATE);
				fos.write(data);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{*/
			OutputStream out = null;
				try {
					out = new BufferedOutputStream(new FileOutputStream(sfile,add));
					out.write(data);
					
				} catch (FileNotFoundException e) {
			    	e.printStackTrace();
			    } catch (IOException e) {
					e.printStackTrace();
				}finally {
			    	if (out != null) {
			    		try {
			    			out.close();
			    		} catch (IOException e) {
			    			e.printStackTrace();
			    		}
			    	}
			   }
		//}
	}
	/*
	public void Write(byte[] b){
		try {
			FileOutputStream fos = MainActivity.ThisActivity.openFileOutput(filename,Context.MODE_PRIVATE);
			fos.write(b);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 */
	
	public void Write(byte...b){
		SetData(b);
		Write();
	}
	
	public void SetData(byte b[]){
		data=new byte[b.length];
		for(int i=0;i<b.length;i++){
			data[i]=b[i];
		}
	}
	
	public static void Write(byte a[],String name){
		 try {
             FileOutputStream fout = new FileOutputStream(name);
             fout.write(a);
             fout.close();
		 } catch (FileNotFoundException e) {
             e.printStackTrace();
     } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static String Read(File f){
		byte b[]=null;
		InputStream a= null;
		String abc="a";
		FileInputStream fls=null;
		try {
			fls=new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		a=new BufferedInputStream(fls);
		try {
			abc="c";
			b=new byte[a.available()];
			abc="d";
			a.read(b);
			abc="e";
			a.close();
			abc="f";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return abc;
	}
	
	public String CountNoNull(){
		String r="";
		for(int i=0;i<data.length;i++){
			if(data[i]!=0){r+=data[i]+" ";}
		}
		return r;
	}

	public String GetName() {
		if(extern){
			if(sfile!=null){
				return sfile.getName();
			}
		}else{
			if(filename!=null){
				return filename;
			}
		}
		return "newFile.mdf";
	}

}
