from PIL import Image
from numpy import *
from pylab import *
import pca
import sys
imlist=[sys.argv[1]]
im = array(Image.open(sys.argv[1])) # open one image to get size
m,n = im.shape[0:2] # get the size of the images
#imnbr = len(imlist) # get the number of images
print(m,n)
# create matrix to store all flattened images
immatrix = array([array(Image.open(im)).flatten()
              for im in imlist],'f')
print("Shape: {} ".format(immatrix.shape))
# perform PCA
V,S,immean = pca.pca(immatrix)

# show some images (mean and 7 first modes)
figure()
gray()
subplot(2,4,1)
imshow(immean.reshape(m,n))
for i in range(7):
  subplot(2,4,i+2)
  imshow(V[i].reshape(m,n))

show()
