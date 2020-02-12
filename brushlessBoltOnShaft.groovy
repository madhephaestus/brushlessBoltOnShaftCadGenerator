import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "brushlessBoltOnShaft"
	if(args==null)
		args=["sunnysky_x2204"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	LengthParameter boltLength		= new LengthParameter("Bolt Length",10,[180,10])
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
	boltLength.setMM(boltLengthValue+1)
	CSG vitamin_capScrew = Vitamins.get("capScrew", boltSizeValue)
							.toZMin()
							.movez(collarDiameterValue/2-1)
							.rotx(90)
							.movez(motorTopToCenterOfBoltValue)
	vitamin_capScrew=vitamin_capScrew.union(vitamin_capScrew.rotz(180))
	CSG shaft = new Cylinder(measurments.shaftDiameter/2,measurments.shaftLength).toCSG()
	// Stub of a CAD object
	CSG part = new Cylinder(collarDiameterValue/2,colarHeightValue).toCSG()
				.union(vitamin_capScrew)
				.union(shaft)
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate()