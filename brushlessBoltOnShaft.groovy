import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "brushlessBoltOnShaft"
	if(args==null)
		args=["sunnysky_x2204"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def boltLengthValue = measurments.boltLength
	def colarHeightValue = measurments.colarHeight
	def collarDiameterValue = measurments.collarDiameter
	def boltSizeValue = measurments.boltSize
	def motorTopToCenterOfBoltValue = measurments.motorTopToCenterOfBolt
	def numberOfBoltsValue = measurments.numberOfBolts
	println "Measurment boltLengthValue =  "+boltLengthValue
	println "Measurment colarHeightValue =  "+colarHeightValue
	println "Measurment collarDiameterValue =  "+collarDiameterValue
	println "Measurment boltSizeValue =  "+boltSizeValue
	println "Measurment motorTopToCenterOfBoltValue =  "+motorTopToCenterOfBoltValue
	println "Measurment numberOfBoltsValue =  "+numberOfBoltsValue
	// Stub of a CAD object
	CSG part = new Cube().toCSG()
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 