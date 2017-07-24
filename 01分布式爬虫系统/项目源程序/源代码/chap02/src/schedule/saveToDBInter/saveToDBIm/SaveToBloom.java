package schedule.saveToDBInter.saveToDBIm;

import model.Bloom;
import bloomFilter.BloomFilter;


public class SaveToBloom
{
	public boolean savetobloom(String url)
	{
	    BloomFilter b=Bloom.bloom;
        if(!b.contains(url))
        {
        	b.addValue(url);
        	return true;
        }
        else
        {
        	System.out.println(url+"已存在");
        }
		return false;
		
	}
}
