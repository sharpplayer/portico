/*
 *   Copyright 2009 The Portico Project
 *
 *   This file is part of portico.
 *
 *   portico is free software; you can redistribute it and/or modify
 *   it under the terms of the Common Developer and Distribution License (CDDL)
 *   as published by Sun Microsystems. For more information see the LICENSE file.
 *
 *   Use of this software is strictly AT YOUR OWN RISK!!!
 *   If something bad happens you do not have permission to come crying to me.
 *   (that goes for your lawyer as well)
 *
 */
#include "HLA13Private.h"
#include "jni/Runtime.h"

#ifdef BUILDING_DLC
namespace rti13 {
#endif

//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// Constructors ////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
RTIambPrivateRefs::RTIambPrivateRefs()
{
	this->rti = portico13::Runtime::getRuntime()->newRtiAmbassador();
}

RTIambPrivateRefs::~RTIambPrivateRefs()
{
	delete this->rti;
}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// Instance Methods //////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
portico13::JavaRTI* RTIambPrivateRefs::getRti()
{
	return this->rti;
}

//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// Static Methods ///////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

#ifdef BUILDING_DLC
}; // namespace
#endif
