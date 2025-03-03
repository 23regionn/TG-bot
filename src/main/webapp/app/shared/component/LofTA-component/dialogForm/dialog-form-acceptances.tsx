import { Dialog } from 'primereact/dialog';
import React from 'react';

interface InitializationParams {
  styleDialog?: any;
  headerDialog: any;
  footerDialog?: () => void;
  onHideDialog: () => void;
  draggableDialog?: boolean;
  bodyDialog?: any;
}

const DialogComponentAcceptances = (props: InitializationParams) => {
  const styleDialogDefault = {
    width: '56.25rem',
  };

  const bodyDefault = () => {
    return <div></div>;
  };

  const headerBody = () => {
    return <div className="d-flex justify-content-center font-weight-bold heading mb-4">{props.headerDialog}</div>;
  };

  return (
    <Dialog
      modal
      visible={true}
      style={props.styleDialog ? props.styleDialog : styleDialogDefault}
      header={headerBody}
      footer={props.footerDialog ? props.footerDialog : null}
      onHide={props.onHideDialog}
      draggable={props.draggableDialog ? props.draggableDialog : false}
    >
      {props.bodyDialog ? props.bodyDialog : bodyDefault}
    </Dialog>
  );
};

export default DialogComponentAcceptances;
