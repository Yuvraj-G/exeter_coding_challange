import java.util.regex.Matcher; 
import java.util.regex.Pattern;
import java.io.*;
import java.util.*;
import java.io.FileWriter;  

class Exeter{
	public static void main(String...k)throws Exception{
		//used to find the time
		long startTime = System.nanoTime();
		
		
		/**
				Reading the csv file and store in the two different ArrayList
				1st Column in one ArrayList 
				2nd Column in second ArrayList
		*/
		ArrayList<String> al1=new ArrayList<>();
		ArrayList<String> al2=new ArrayList<>();
		int aa[]=new int[1000];
		String line = "";  
		String splitBy = ",";  
		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\yuvraj\\Desktop\\french_dictionary.csv"));  
			int i=0;
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
				String[] employee = line.split(splitBy);    // use comma as separator  
				al1.add(employee[0]);
				al2.add(employee[1]);
				i++;
			}  
		}catch (IOException e){  
			e.printStackTrace();  
		}  
		
		/**
			Reading the file t8.shakespeare.txt line by line;
		*/
		File file=new File("C:\\Users\\yuvraj\\Desktop\\t8.shakespeare.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		String a="";
  
		while ((st = br.readLine()) != null){
			a=st;
			String s[]=a.split(" ");
			FileWriter fw=new FileWriter("C:\\Users\\yuvraj\\Desktop\\t8.shakespeare.translator.txt",true);
			for(String i:s){
				String j=i.toLowerCase();
				//for end 
				if(al1.contains(j)){
					int index=al1.indexOf(j);
					aa[index]=aa[index]+1;
					fw.write(al2.get(index)+" ");
			  
				}
				else{
					Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
					Matcher matcher = pattern.matcher(j);
					boolean isStringContainsSpecialCharacter = matcher.find();
					String charstring="",specialstringEnding="",specialstringStrating="";
					int specialCharactersIndexForEnding=0;
					int specialCharactersIndexForStarting=0;
					char c[]=j.toCharArray();
					if(isStringContainsSpecialCharacter){
						//for ending
						for(int ii=c.length-1;ii>0;ii--){
							if(c[ii]=='-' || c[ii]=='.' || c[ii]=='?' ||c[ii]=='"' || c[ii]=='\'' || c[ii]=='*' || c[ii]=='!' || c[ii]==','|| c[ii]==':' || c[ii]==';' || c[ii]=='&' || c[ii]=='(' || c[ii]==')' || c[ii]=='{' || c[ii]=='}' || c[ii]=='[' || c[ii]==']' || c[ii]=='>'){
								specialstringEnding=specialstringEnding+c[ii];
							}
							else
								break;
						}
						for(int ii=0;ii<c.length;ii++){
							if(c[ii]=='-' || c[ii]=='.' || c[ii]=='?' ||c[ii]=='"' || c[ii]=='\'' || c[ii]=='*' || c[ii]=='!' || c[ii]==','|| c[ii]==':' || c[ii]==';' || c[ii]=='&' || c[ii]=='(' || c[ii]==')' || c[ii]=='{' || c[ii]=='}' || c[ii]=='[' || c[ii]==']' || c[ii]=='>'){
								specialstringStrating=specialstringStrating+c[ii];
							}
							else
								break;
						}
						int countofspecialstringEnding=specialstringEnding.length();
						String substrEnding=j.substring(0,j.length()-countofspecialstringEnding);
						int countofspecialstringStrating=specialstringStrating.length();
						String substrStarting=j.substring(countofspecialstringStrating,j.length());
				
						if(al1.contains(substrEnding)){
							int index=al1.indexOf(substrEnding);
							aa[index]=aa[index]+1;
							fw.write(al2.get(index)+specialstringEnding+" ");
						}
						else if(al1.contains(substrStarting)){
							int index=al1.indexOf(substrStarting);
							aa[index]=aa[index]+1;
							fw.write(specialstringStrating+al2.get(index)+" ");
						}
						else{
							fw.write(j+" ");
						}
					}
					else{
						fw.write(j+" ");
					}
				}
			}
			fw.write("\n");
			fw.close();
		}
 
 
		/**
			Printing the frequency of each word int the file FrequecyOfWords.txt
		*/
		for(int i=0;i<1000;i++){
			try{
				FileWriter fw=new FileWriter("C:\\Users\\yuvraj\\Desktop\\FrequecyOfWords.txt",true);
				fw.write(al1.get(i)+", "+al2.get(i)+", "+aa[i] +"\n");
				fw.close();
			}catch(Exception e){System.out.println(e);}    		   
		}
		System.out.println("Success...");
		long endTime = System.nanoTime();
System.out.println("Took "+(endTime - startTime) + " ns");

	}
}
