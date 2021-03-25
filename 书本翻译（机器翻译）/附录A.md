# 附录A:GL_HALF_FLOAT_OES

GL_HALF_FLOAT_OES是OpenGL  ES 2.0支持的可选顶点数据格式。实现这种顶点数据格式的扩展字符串叫做GL_OES_vertex_half_float。要确定OpenGL ES  2.0实现是否支持此功能，请在glGetString(GL_EXTENSIONS)返回的扩展列表中查找字符串名称GL_OES_vertex_half_float。 

GL_HALF_FLOAT_OES is an optional vertex data format supported by OpenGL ES  2.0. The extension string that implements this vertex data format is named  GL_OES_vertex_half_float. To determine whether this feature is supported by an  OpenGL ES 2.0 implementation, look for the string name GL_OES_vertex_half_float  in the list of extensions returned by glGetString(GL_EXTENSIONS).

GL  _ HALF _ FLOAT _  OES顶点数据类型用于指定16位浮点顶点数据属性。这在指定顶点属性(如纹理坐标、法线、副法线和切线向量)时非常有用。在GL_FLOAT上使用GL_HALF_FLOAT_OES可以将内存占用减少两倍。此外，GPU读取顶点属性所需的内存带宽也减少了大约两倍。

The GL_HALF_FLOAT_OES vertex data type is used to specify 16-bit floatingpoint  vertex data attributes. This can be very useful in specifying vertex attributes  such as texture coordinates, normal, binormal, and tangent vectors. Using  GL_HALF_FLOAT_OES over GL_FLOAT provides a two times reduction in memory  footprint. In addition, memory bandwidth required to read vertex attributes by  the GPU is also reduced by approximately two times.

有人可能会说，我们可以使用GL_SHORT或GL_UNSIGNED_SHORT来代替16位浮点数据类型，并获得相同的内存占用和带宽节省。然而，你现在需要适当地缩放数据或矩阵，并在顶点着色器中应用变换。例如，考虑一个纹理图案在一个四边形上水平和垂直重复四次的情况。GL_SHORT可以用来存储纹理坐标。纹理坐标可以存储为4.12或8.8的值。存储为GL_SHORT的纹理坐标值按(1  << 12)或(1 << 8)进行缩放，以给出使用4位或8位整数和12位或8位小数的定点表示。因为OpenGL  ES不理解这样的格式，顶点着色器将需要应用矩阵来缩放这些值，这影响了顶点着色性能。如果使用16位浮点格式，则不需要这些额外的转换。

One can argue that we can use GL_SHORT or GL_UNSIGNED_SHORT instead of a  16-bit floating-point data type and get the same memory footprint and bandwidth  savings. However you will now need to scale the data or matrices appropriately  and apply a transform in the vertex shader. For example, consider the case where  a texture pattern is to be repeated four times horizontally and vertically over  a quad. GL_SHORT can be used to store the texture coordinates. The texture  coordinates could be stored as a 4.12 or 8.8 value. The texture coordinate  values stored as GL_SHORT are scaled by (1 << 12) or (1 << 8) to  give us a fixed-point representation that uses 4 bits or 8 bits of integer and  12 bits or 8 bits of fraction. Because OpenGL ES does not understand such a  format, the vertex shader will then need to apply a matrix to unscale these  values, which impacts the vertex shading performance. These additional  transforms are not required if a 16-bit floating-point format is used.

注意:请注意，如果使用GL_SHORT来描述纹理坐标，纹理坐标可能会使用定点生成。这意味着不同的误差度量，因为浮点数的绝对误差与值的大小成比例，而定点格式的绝对误差是常数。开发人员在为特定格式生成坐标时选择使用哪种数据类型时，需要注意这些精度问题。

Note: Note that if GL_SHORT is used to describe the texture coordinates, the  texture coordinates will probably be generated using fixed-point. This implies a  different error metric as the absolute error in a floatingpoint number is  proportional to the magnitude of the value, whereas absolute error in a  fixed-point format is constant. Developers need to be aware of these precision  issues when choosing which data type to use when generating coordinates for a  particular format.