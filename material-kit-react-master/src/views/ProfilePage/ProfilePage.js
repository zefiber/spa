import React, { useEffect } from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// @material-ui/icons
import Camera from "@material-ui/icons/Camera";
import Palette from "@material-ui/icons/Palette";
import Favorite from "@material-ui/icons/Favorite";
// core components
import axios from 'axios'

import Header from "components/Header/Header.js";
import Footer from "components/Footer/Footer.js";
import Button from "components/CustomButtons/Button.js";
import GridContainer from "components/Grid/GridContainer.js";
import AdvancedGridList from "components/Grid/AdvancedGridLine.js";
import GridItem from "components/Grid/GridItem.js";
import NavPills from "components/NavPills/NavPills.js";
import Parallax from "components/Parallax/Parallax.js";

import profile from "assets/img/faces/starwar.jpeg";
import styles from "assets/jss/material-kit-react/views/profilePage.js";


const useStyles = makeStyles(styles);


export default function ProfilePage(props) {

  const classes = useStyles();
  const { ...rest } = props;
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);

 

  return (
    <div>
      <Header
        color="transparent"
        brand="Ze's Star War Machete"
        fixed
        changeColorOnScroll={{
          height: 200,
          color: "white"
        }}
        {...rest}
      />
      <Parallax small filter image={require("assets/img/profile-bg.jpg")} />
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div>
                    <img src={profile} alt="..." className={imageClasses} />
                  </div>
                  <div className={classes.name}>
                    <h3 className={classes.title}>May the Force be with you.</h3>
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <p>
              Currently there does not seem to be any good online resource that displays the Star Wars movies in
              story order (Episode I-VII) as opposed to chronologically (1978-2015). Worse, they are not optionally
              presented in the obviously superior “Machete” order, which, as everyone knows, is the superior order
              to watch them.{" "}
              </p>
            </div>
            <GridContainer justify="center">
              <GridItem xs={12} sm={8} md={8} className={classes.navWrapper}>
                <NavPills
                  alignCenter
                  color="primary"
                  tabs={[
                    {
                      tabButton: "Episode",
                      tabIcon: Camera,
                      tabContent: (
                        <AdvancedGridList sort="episode"></AdvancedGridList>
                      )
                    },
                    {
                      tabButton: "Release",
                      tabIcon: Palette,
                      tabContent: (
                        <AdvancedGridList sort="release"></AdvancedGridList>
                      )
                    },
                    {
                      tabButton: "Machete",
                      tabIcon: Favorite,
                      tabContent: (
                        <AdvancedGridList sort="machete"></AdvancedGridList>
                      )
                    }
                  ]}
                />
              </GridItem>
            </GridContainer>
          </div>
          
        </div>
      </div>
      <Footer />
    </div>
  );
}
