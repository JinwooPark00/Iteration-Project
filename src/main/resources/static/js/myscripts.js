//<script src="https://cdn.jsdelivr.net/npm/tsparticles-slim@2.0.6/tsparticles.slim.bundle.min.js"></script>
//<script type="text/javascript">
      const options = {
        background: {
          color: "#e9ecef", // the canvas background color
        },
        interactivity: {
          events: {
            onClick: {
              // this handles the mouse click event
              enable: true,
              mode: "push", // this adds particles
            },
            onHover: {
              // this handles the mouse hover event
              enable: true,
              mode: "repulse", // this make particles move away from the mouse
            },
          },
          modes: {
            push: {
              quantity: 6, // number of particles to add
            },
            repulse: {
              distance: 100, // the distance of the particles from the mouse
            },
          },
        },
        particles: {
            links: {
            color: "#607385",
            distance: 150,
            enable: true,
            opacity: 0.3,
            width: 1
          },
          move: {
            enable: true, // this makes particles move
            speed: { min: 1, max: 3 }, // this is the speed of the particles
          },
          opacity: {
            value: { min: 0.3, max: 0.5 }, // this sets the opacity of the particles
          },
          size: {
            value: { min: 1, max: 3 }, // this sets the size of the particles
          },
          color: {
            value: "#607385",
          }
        },
        fullScreen: { enable: false },
      };
       tsParticles.load("particles", options);
//</script>