package weather;

import java.io.IOException;
import java.util.Formatter;

import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.constants.FeatureType;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dt.RadialDatasetSweep;
import ucar.nc2.ft.FeatureDataset;
import ucar.nc2.ft.FeatureDatasetFactoryManager;

public class NetCdfFactory {

	public static RadialDatasetSweep getRadialDatasetSweep(String filename)
	{
		try {
			Formatter formatter = new Formatter();
			FeatureDataset fDataSet = FeatureDatasetFactoryManager.open(FeatureType.RADIAL, filename, null, formatter);
			if (fDataSet == null)
			{
				return null;
			}
			
			if (fDataSet.getFeatureType() != FeatureType.RADIAL)
			{
				return null;
			}
			
			return (RadialDatasetSweep) fDataSet;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
