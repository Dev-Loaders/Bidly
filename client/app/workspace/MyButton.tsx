import React from 'react';

export const MyButton = (props: {
  children: React.ReactNode
}) => {
  
  return (
    <button>
      {props.children}
    </button>
  )
}

