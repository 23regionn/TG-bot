import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './edit-channels.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEditChannelsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EditChannelsDetail = (props: IEditChannelsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { editChannelsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="editChannelsDetailsHeading">EditChannels</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{editChannelsEntity.id}</dd>
          <dt>
            <span id="idMessage">Id Message</span>
          </dt>
          <dd>{editChannelsEntity.idMessage}</dd>
          <dt>
            <span id="idChannel">Id Channel</span>
          </dt>
          <dd>{editChannelsEntity.idChannel}</dd>
          <dt>
            <span id="dateCreateMessage">Date Create Message</span>
          </dt>
          <dd>
            {editChannelsEntity.dateCreateMessage ? (
              <TextFormat value={editChannelsEntity.dateCreateMessage} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lastNameChannel">Last Name Channel</span>
          </dt>
          <dd>{editChannelsEntity.lastNameChannel}</dd>
          <dt>
            <span id="newNameChannel">New Name Channel</span>
          </dt>
          <dd>{editChannelsEntity.newNameChannel}</dd>
          <dt>
            <span id="isAproveChange">Is Aprove Change</span>
          </dt>
          <dd>{editChannelsEntity.isAproveChange}</dd>
          <dt>
            <span id="lastLinkToChannel">Last Link To Channel</span>
          </dt>
          <dd>{editChannelsEntity.lastLinkToChannel}</dd>
          <dt>
            <span id="newlastLinkToChannel">Newlast Link To Channel</span>
          </dt>
          <dd>{editChannelsEntity.newlastLinkToChannel}</dd>
          <dt>
            <span id="lastPriceChannel">Last Price Channel</span>
          </dt>
          <dd>{editChannelsEntity.lastPriceChannel}</dd>
          <dt>
            <span id="newPriceChannel">New Price Channel</span>
          </dt>
          <dd>{editChannelsEntity.newPriceChannel}</dd>
          <dt>
            <span id="addDescriptionAboutChannel">Add Description About Channel</span>
          </dt>
          <dd>{editChannelsEntity.addDescriptionAboutChannel}</dd>
          <dt>
            <span id="currentDescriptionChannel">Current Description Channel</span>
          </dt>
          <dd>{editChannelsEntity.currentDescriptionChannel}</dd>
          <dt>
            <span id="addRegionChannel">Add Region Channel</span>
          </dt>
          <dd>{editChannelsEntity.addRegionChannel}</dd>
          <dt>
            <span id="editRegionChannel">Edit Region Channel</span>
          </dt>
          <dd>{editChannelsEntity.editRegionChannel}</dd>
          <dt>
            <span id="addCityChannel">Add City Channel</span>
          </dt>
          <dd>{editChannelsEntity.addCityChannel}</dd>
          <dt>
            <span id="editCityChannel">Edit City Channel</span>
          </dt>
          <dd>{editChannelsEntity.editCityChannel}</dd>
          <dt>
            <span id="userId">User Id</span>
          </dt>
          <dd>{editChannelsEntity.userId}</dd>
          <dt>
            <span id="userName">User Name</span>
          </dt>
          <dd>{editChannelsEntity.userName}</dd>
          <dt>
            <span id="isApprovedChanhes">Is Approved Chanhes</span>
          </dt>
          <dd>{editChannelsEntity.isApprovedChanhes ? 'true' : 'false'}</dd>
          <dt>
            <span id="comment">Comment</span>
          </dt>
          <dd>{editChannelsEntity.comment}</dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{editChannelsEntity.status}</dd>
          <dt>
            <span id="serviceField1">Service Field 1</span>
          </dt>
          <dd>{editChannelsEntity.serviceField1}</dd>
          <dt>
            <span id="serviceField2">Service Field 2</span>
          </dt>
          <dd>{editChannelsEntity.serviceField2}</dd>
          <dt>
            <span id="serviceField3">Service Field 3</span>
          </dt>
          <dd>{editChannelsEntity.serviceField3}</dd>
        </dl>
        <Button tag={Link} to="/edit-channels" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/edit-channels/${editChannelsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ editChannels }: IRootState) => ({
  editChannelsEntity: editChannels.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EditChannelsDetail);
