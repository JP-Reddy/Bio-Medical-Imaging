import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

class pca{


	private static int[][][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][][] result = new int[height][width][4];
		if (hasAlphaChannel) {
		  final int pixelLength = 4;
		  for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
		     result[row][col][3]= ((int) pixels[pixel] & 0xff); // alpha
		     result[row][col][2]= ((int) pixels[pixel + 1] & 0xff); // blue
		     result[row][col][1]= ((int) pixels[pixel + 2] & 0xff); // green
		     result[row][col][0]= ((int) pixels[pixel + 3] & 0xff); // red
		     col++;
		     if (col == width) {
		        col = 0;
		        row++;
		     }
		  }
		} else {
		  final int pixelLength = 3;
		  for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {

				result[row][col][2]= ((int) pixels[pixel] & 0xff); // blue
				result[row][col][1]= ((int) pixels[pixel+ 1] & 0xff); // green
 				result[row][col][0]= ((int) pixels[pixel + 2] & 0xff); // red
				col++;
		     if (col == width) {
		        col = 0;
		        row++;
		     }
		  }
		}

		return result;
		}

	public static void main(String[] args){
		String url="woman.jpg";

		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(url));
		} 
		catch (IOException e) {
			System.out.println(e);
		}
		int[][][] result = convertTo2DWithoutUsingGetRGB(img);
		System.out.println(img.getHeight());
		int height=img.getHeight();
		int width=img.getWidth();
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++){
				for(int k=0;k<2;k++)
				System.out.println(result[i][j][k]);
			}
		

	}

}